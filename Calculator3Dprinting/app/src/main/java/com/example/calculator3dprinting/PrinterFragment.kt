package com.example.calculator3dprinting

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class PrinterFragment: Fragment() {
    private lateinit var printerList: MutableList<PrinterData>
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.printer_fragment, container, false)
        listView = view.findViewById(R.id.ListView)
        populateListView(view)

        // User pushes the Add Printer button
        val addPrinterButton: Button = view.findViewById(R.id.addPrinterButton)
        addPrinterButton.setOnClickListener {
            showAddPrinterDialog()
        }

        // User selects an exisint printer from the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            // Handle item click (edit or delete)
            val selectedPrinter: PrinterData = printerList[position]
            showEditOrDeleteDialog(selectedPrinter, position)
        }

        return view
    }

    private fun populateListView(view: View) {
        // Retrieve printer list from preferences or database
        // For demonstration purposes, using a sample list
        printerList = mutableListOf(
            PrinterData("Cetus", 1.75, 450.00, 5000.0, 100.00, 0.150),
            PrinterData("Ultimaker 3 Extended", 2.85, 4400.00, 4000.0, 250.00, 0.110)
        )

        //printerList.sortBy { it.name }

        val adapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_list_item_1,
            printerList.map { it.name })
        listView.adapter = adapter

        //Log.d("PrinterFragment", "Printer List: $printerList")
        //Log.d("PrinterFragment", "Number of Items: ${printerList.size}")
        //Log.d("PrinterFragment", "Adapter set with data: ${printerList.map { it.name }}")
    }

    private fun refreshListView() {
        // Update the ListView when the printerList is modified
        printerList.sortBy { it.name }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            printerList.map { it.name })
        listView.adapter = adapter
        adapter.notifyDataSetChanged()

        Log.d("PrinterFragment", "Printer List: $printerList")
        Log.d("PrinterFragment", "Number of Items: ${printerList.size}")
    }

    private fun showAddPrinterDialog() {
        // Implement a dialog to capture printer properties and add to the list
        // Use AlertDialog.Builder or create a custom dialog
        // Update printerList and refresh the ListView
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add Printer")

        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.add_printer_dialog, null)
        builder.setView(dialogView)

        val nameEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val diameterEditText = dialogView.findViewById<EditText>(R.id.diameterEditText)
        val priceEditText = dialogView.findViewById<EditText>(R.id.priceEditText)
        val depreciationEditText = dialogView.findViewById<EditText>(R.id.depreciationEditText)
        val serviceEditText = dialogView.findViewById<EditText>(R.id.serviceEditText)
        val energyEditText = dialogView.findViewById<EditText>(R.id.energyEditText)

        builder.setPositiveButton("Add", null)
        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()

        // Set an OnShowListener to prevent the dialog from closing when the positive button is clicked
        dialog.setOnShowListener {
            val positiveButton = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                // Check if any field is empty
                if (nameEditText.text.isBlank() || diameterEditText.text.isBlank() ||
                    priceEditText.text.isBlank() || depreciationEditText.text.isBlank() ||
                    serviceEditText.text.isBlank() || energyEditText.text.isBlank()
                ) {
                    // Show a toast message indicating that all fields must be filled
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_LONG)
                        .show()
                } else {
                    // All fields are filled, proceed to add the printer
                    val newPrinter = PrinterData(
                        nameEditText.text.toString(),
                        diameterEditText.text.toString().toDouble(),
                        priceEditText.text.toString().toDouble(),
                        depreciationEditText.text.toString().toDouble(),
                        serviceEditText.text.toString().toDouble(),
                        energyEditText.text.toString().toDouble()
                    )

                    printerList.add(newPrinter)
                    refreshListView()
                    dialog.dismiss()
                }
            }
        }

        // Show the dialog
        dialog.show()
    }

    private fun showEditOrDeleteDialog(selectedPrinter: PrinterData, index: Int) {
        // Implement a dialog to edit or delete the selected printer
        // Use AlertDialog.Builder or create a custom dialog
        // Update printerList and refresh the ListView
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Edit/Delete Printer")

        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.edit_printer_dialog, null)
        builder.setView(dialogView)

        val currentNameTextView: TextView = dialogView.findViewById(R.id.currentName)
        currentNameTextView.text = "Current Name: ${selectedPrinter.name}"
        val currentDiameterTextView: TextView = dialogView.findViewById(R.id.currentDiameter)
        currentDiameterTextView.text = "Current Name: ${selectedPrinter.materialDiameter}"
        val currentPriceTextView: TextView = dialogView.findViewById(R.id.currentPrice)
        currentPriceTextView.text = "Current Name: ${selectedPrinter.price}"
        val currentDepTextView: TextView = dialogView.findViewById(R.id.currentDepreciation)
        currentDepTextView.text = "Current Name: ${selectedPrinter.depreciationTime}"
        val currentServTextView: TextView = dialogView.findViewById(R.id.currentSvcCost)
        currentServTextView.text = "Current Name: ${selectedPrinter.serviceCostsPerLife}"
        val currentEnergyTextView: TextView = dialogView.findViewById(R.id.currentEnergy)
        currentEnergyTextView.text = "Current Name: ${selectedPrinter.energyConsumption}"

        builder.setPositiveButton("Update") { _, _ ->
            val nameEditText = dialogView.findViewById<EditText>(R.id.nameEditTextED)
            val diameterEditText = dialogView.findViewById<EditText>(R.id.diameterEditTextED)
            val priceEditText = dialogView.findViewById<EditText>(R.id.priceEditTextED)
            val depreciationEditText =
                dialogView.findViewById<EditText>(R.id.depreciationEditTextED)
            val serviceEditText = dialogView.findViewById<EditText>(R.id.serviceEditTextED)
            val energyEditText = dialogView.findViewById<EditText>(R.id.energyEditTextED)

            val updatedPrinter: PrinterData = selectedPrinter
            if (nameEditText.text.isNotEmpty()) updatedPrinter.name = nameEditText.text.toString()
            if (diameterEditText.text.isNotEmpty()) updatedPrinter.materialDiameter =
                diameterEditText.text.toString().toDouble()
            if (priceEditText.text.isNotEmpty()) updatedPrinter.price =
                priceEditText.text.toString().toDouble()
            if (depreciationEditText.text.isNotEmpty()) updatedPrinter.depreciationTime =
                depreciationEditText.text.toString().toDouble()
            if (serviceEditText.text.isNotEmpty()) updatedPrinter.serviceCostsPerLife =
                serviceEditText.text.toString().toDouble()
            if (energyEditText.text.isNotEmpty()) updatedPrinter.energyConsumption =
                energyEditText.text.toString().toDouble()


            printerList[index] = updatedPrinter
            refreshListView()
            builder.create().dismiss()
        }

        val deleteButton: Button = dialogView.findViewById(R.id.btnDelete)
        deleteButton.setOnClickListener {
            // Handle delete button click
            onDeleteButtonClicked(selectedPrinter)
            // Dismiss the dialog after deletion
            builder.create().dismiss()
        }

        // Set a custom negative button click listener to handle the cancel logic
        //dialog.setOnCancelListener { dialog.dismiss() }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun onDeleteButtonClicked(selectedPrinter: PrinterData) {
        // Handle deletion logic here
        // Remove selectedPrinter from your data source (e.g., printerList)
        printerList.remove(selectedPrinter)
        // Refresh the ListView or update your data source accordingly
        refreshListView()
    }

}
    /*
    private val TAG = "PrinterFragment"
    private lateinit var pMap: PrinterManager
    private lateinit var mListView: ListView
    private lateinit var sharedPreferences: SharedPreferences
    //private val db = DatabaseManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.printer_fragment, container, false)
        mListView = view.findViewById(R.id.ListView)

        val newPrinter: FloatingActionButton = view.findViewById(R.id.addPrinter)
        newPrinter.setOnClickListener {
            val intent = Intent(this.context, PrinterAddActivity::class.java)
            startActivity(intent)
        }
        //AsyncTask.execute() {
        populateListView(mListView, view)
        //}

        Log.d(TAG, "ListView: Populated")
        mListView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, i, _ ->
                val name = adapterView.getItemAtPosition(i).toString()
                Log.d(TAG, "onItemClick: You Clicked on $name")

                val itemId = pMap.getIdByName(name) //get the id associated with that name

                Log.d(TAG, "onItemClick: The ID is: $itemId")
                val editScreenIntent =
                    Intent(view.context, PrinterEditActivity::class.java)
                editScreenIntent.putExtra("id", itemId)
                startActivity(editScreenIntent)
            }
        return view
    }

    private fun populateListView(mListView: ListView, view: View) {
        /*
        db.context = view.context
        db.initialize()

        val data = db.getPrinterNames()

        val adapter = ArrayAdapter(
            view.context, android.R.layout.simple_list_item_1, data
        )
        mListView.adapter = adapter

        mListView.onItemClickListener =
            OnItemClickListener { adapterView, _, i, _ ->
                val name = adapterView.getItemAtPosition(i).toString()
                Log.d(TAG, "onItemClick: You Clicked on $name")

                val itemId = db.getPrinterIdByName(name) //get the id associated with that name

                Log.d(TAG, "onItemClick: The ID is: $itemId")
                val editScreenIntent =
                    Intent(view.context, PrinterEditActivity::class.java)
                editScreenIntent.putExtra("id", itemId)
                startActivity(editScreenIntent)
            }
    }
    */
        sharedPreferences = requireContext().getSharedPreferences("Printers", MODE_PRIVATE)
        pMap = PrinterManager(sharedPreferences.all as MutableMap<Int, Printers>)

        val pList = pMap.hashPrinter!!.values.toList()
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, pList)
        mListView.adapter = adapter
    }
}
*/

