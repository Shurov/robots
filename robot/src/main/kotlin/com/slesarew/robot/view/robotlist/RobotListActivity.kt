package com.slesarew.robot.view.robotlist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater.from
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.slesarew.robot.R
import com.slesarew.robot.domain.Injector
import com.slesarew.robot.view.robotdetails.RobotDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_robot_list.progress
import com.slesarew.robot.domain.Robot as DomainRobot
import com.slesarew.robot.view.Robot as ViewRobot
import kotlinx.android.synthetic.main.activity_robot_list.robot_list as robotList

class RobotListActivity :
        AppCompatActivity(),
        AddRobotDialog.OnRobotAddedListener {

    private val robotUseCase by lazy {
        Injector.getRobotUseCase()
    }

    private var robotUseCaseDisposable = Disposables.empty()

    private val robots = mutableListOf<ViewRobot>()

    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_robot_list)

        layoutManager = LinearLayoutManager(this)
        adapter = RobotAdapter(robots) {
            val intent = Intent(this, RobotDetailsActivity::class.java)
            intent.putExtras(ViewRobot.bundle(it))
            startActivity(intent)
        }

        robotList.setHasFixedSize(true)
        robotList.layoutManager = layoutManager
        robotList.adapter = adapter
        robotList.addItemDecoration(DividerItemDecoration(this,
                                                          DividerItemDecoration.VERTICAL))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_robot_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_robot -> {
                val addRobotDialog = AddRobotDialog()
                addRobotDialog.show(supportFragmentManager,
                                    "robotDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRobotAdded(name: String) {
        robotUseCaseDisposable = robotUseCase.getRobot(name)
                .subscribeOn(Schedulers.io())
                .map { robotMapper(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.visibility = View.VISIBLE
                }
                .subscribe { robot: com.slesarew.robot.view.Robot ->
                    progress.visibility = View.INVISIBLE
                    robots.add(robot)
                    adapter.notifyDataSetChanged()
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (robotUseCaseDisposable.isDisposed) {
            robotUseCaseDisposable.dispose()
        }
    }
}

fun robotMapper(robot: DomainRobot) = ViewRobot(robot.name, robot.image)

private class RobotAdapter(private val robots: MutableList<ViewRobot>,
                           private val onRobotClicked: (name: String) -> Unit) : RecyclerView.Adapter<RobotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {

        val view = from(parent.context).inflate(R.layout.robot_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val robot = robots[position]
        holder.name?.text = robot.name
        holder.image?.setImageDrawable(robot.image)
        holder.view.setOnClickListener {
            onRobotClicked.invoke(robot.name)
        }
    }

    override fun getItemCount() = robots.size

    private class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView? = null
        var image: ImageView? = null

        init {
            name = view.findViewById(R.id.robot_name)
            image = view.findViewById(R.id.robot_image)
        }
    }
}