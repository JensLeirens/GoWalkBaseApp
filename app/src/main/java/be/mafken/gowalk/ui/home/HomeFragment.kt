package be.mafken.gowalk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import be.mafken.gowalk.R
import be.mafken.gowalk.extensions.goToFragment
import be.mafken.gowalk.extensions.nonNull
import be.mafken.gowalk.extensions.observe
import be.mafken.gowalk.ui.walk.WalkFragment
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

 companion object {
  fun newInstance() = HomeFragment()
 }

 private val viewModel: HomeViewModel by lazy {
  ViewModelProviders.of(this).get(HomeViewModel::class.java)
 }

 val walkAdapter = WalkAdapter()


 override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?
 ): View? {
  return inflater.inflate(R.layout.home_fragment,
   container, false)
 }

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)

  viewModel.getWalkingsForUserFromDatabase()
  walkRecycler.layoutManager = LinearLayoutManager(context)
  walkRecycler.adapter = walkAdapter


  viewModel.walkList.nonNull().observe(this) {
   walkAdapter.walkings = it
   updateUI()
  }

  floatingActionButton.setOnClickListener {
   activity?.goToFragment("Home",
    WalkFragment.newInstance())
  }
 }

 fun updateUI() {
  viewModel.getSecondsFromWalk()
  walkCardTotalDistance.text = "${viewModel.totalDistance} Km"
  walkCardTotalTime.text =
   "${viewModel.totalHours}h : ${viewModel.totalMinutes}m : ${viewModel.totalSeconds}s"
 }


 override fun onPause() {
  super.onPause()
  viewModel.saveUser()
 }
}
