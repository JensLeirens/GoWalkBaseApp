package be.mafken.gowalk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.mafken.gowalk.R
import be.mafken.gowalk.extensions.nonNull
import be.mafken.gowalk.extensions.observe


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.walkList.nonNull().observe(this){

        }

    }

}
