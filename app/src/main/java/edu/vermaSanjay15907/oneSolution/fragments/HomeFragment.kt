package edu.vermaSanjay15907.oneSolution.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import edu.vermaSanjay15907.oneSolution.R
import edu.vermaSanjay15907.oneSolution.activities.LoginActivity
import edu.vermaSanjay15907.oneSolution.adapters.HomeActivityComplaintListAdapter
import edu.vermaSanjay15907.oneSolution.databinding.FragmentHomeBinding
import edu.vermaSanjay15907.oneSolution.models.Complaint

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
        val complaints = ArrayList<Complaint>()

        val homeActivityComplaintListAdapter =
            activity?.let { HomeActivityComplaintListAdapter(it, complaints) }
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvComplaints.layoutManager = layoutManager
        binding.rvComplaints.isNestedScrollingEnabled = false
        binding.rvComplaints.adapter = homeActivityComplaintListAdapter

        binding
            .btnAddNewComplaint.setOnClickListener {
                Toast.makeText(activity, "Adding a new Complaint", Toast.LENGTH_SHORT).show()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewComplaintFragment())
            }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        auth.signOut()
        Toast.makeText(activity, "Signed out successfully", Toast.LENGTH_SHORT).show()
        startActivity(Intent(activity, LoginActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
}