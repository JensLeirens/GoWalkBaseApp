package be.mafken.gowalk.ui.walk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.mafken.gowalk.R
import kotlinx.android.synthetic.main.walk_fragment.*


class WalkFragment : Fragment() {

 companion object {
  fun newInstance() = WalkFragment()
 }

 private val viewModel: WalkViewModel by lazy {
  ViewModelProviders.of(this).get(WalkViewModel::class.java)
 }

 override fun onCreateView(
  inflater: LayoutInflater, container: ViewGroup?,
  savedInstanceState: Bundle?
 ): View? {
  return inflater.inflate(R.layout.walk_fragment, container, false)
 }

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)
  viewModel.getWalkingsFromDatabase()
  viewModel.incrementWalkingsCreatedTracker()
  walkPickTimeBtn.setOnClickListener {
   val fragment = WalkTimePickerDialog()
   fragment.onOkClicked = object : WalkTimePickerDialog.OnDialogOkClicked {
    override fun onOkClicked(hours: Int, minutes: Int, seconds: Int) {
     viewModel.walk.value!!.time = calculateSeconds(hours, minutes, seconds)
     viewModel.walk.value!!.displayTime = "${hours}h : ${minutes}m : ${seconds}s"
     walkTimeText.text = viewModel.walk.value!!.displayTime
    }

   }
   fragment.show(fragmentManager!!, "Timepicker Dialog")
  }

  walkSaveBtn.setOnClickListener {

   var noError = true
   try {
    viewModel.walk.value?.amountKm = walkDistanceEdit.text.toString().toInt()
   } catch (e: NumberFormatException) {
    noError = false
    walkDistanceEdit.error = "This has to be a number. ex: 5 or 2"
   }

   if (noError) {
    viewModel.saveWalkToDatabase()
    activity?.onBackPressed()
   }
  }

 }

 fun calculateSeconds(hours: Int, minutes: Int, seconds: Int) =
  seconds + minutes * 60 + hours * 60 * 60

}
