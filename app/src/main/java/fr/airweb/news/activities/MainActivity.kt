package fr.airweb.news.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.airweb.news.PSGApplication
import fr.airweb.news.R
import fr.airweb.news.adapters.NewsAdapter
import fr.airweb.news.data.models.Post
import fr.airweb.news.decorators.NewsDecorator
import fr.airweb.news.viewmodels.MainViewModel
import fr.airweb.news.viewmodels.SplashViewModel
import kotlinx.android.synthetic.main.content_main.*
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var splashViewModel: SplashViewModel
    @Inject lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        PSGApplication.appComponent?.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        // ================ Init data calls ================
        initDataCalls()
        // ================ Init views ================
        initViews()
        // ================ Init observers ================
        initObservers()
    }

    private fun initDataCalls() {
        mainViewModel.loadNews()
    }

    private fun initObservers() {
        splashViewModel.news.observe(this, Observer { posts ->
            onPostsLoaded(posts ?: ArrayList<Post>())
        })

        mainViewModel.news.observe(this, Observer { posts ->
            onPostsLoaded(posts ?: ArrayList<Post>())
        })
    }

    private fun onPostsLoaded(posts: ArrayList<Post>) {
        newsRv.adapter = NewsAdapter(posts)
        newsSrl.isRefreshing = false
    }

    private fun initViews() {
        // Recycler view properties
        newsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        newsRv.addItemDecoration(
            NewsDecorator(resources!!.getDimension(R.dimen.news_post_container_padding).toInt())
        )

        // Swipe Refresh Layout
        newsSrl.setColorSchemeColors(
            ContextCompat.getColor(this, R.color.colorPrimary)
        )

        newsSrl.setOnRefreshListener {
            splashViewModel.loadNews()
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)


        }
    }
}
