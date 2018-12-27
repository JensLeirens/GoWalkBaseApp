package be.mafken.gowalk.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.mafken.gowalk.R
import be.mafken.gowalk.extensions.goToFragment
import be.mafken.gowalk.extensions.goToFragmentWithoutBackstack
import be.mafken.gowalk.ui.home.HomeFragment
import be.mafken.gowalk.ui.walk.WalkFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToFragmentWithoutBackstack(HomeFragment.newInstance())

        floatingActionButton.setOnClickListener {
            goToFragment("Home",WalkFragment.newInstance())
        }
    }
}
