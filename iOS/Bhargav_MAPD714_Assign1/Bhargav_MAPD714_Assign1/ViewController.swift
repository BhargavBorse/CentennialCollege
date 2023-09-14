//
//  ViewController.swift
//  Bhargav_MAPD714_Assign1
//
// Created by Bhargav Borse on 2023-09-14.
// Student ID: 301278352
// Description: This app calculates simple interest based on user input for principal, rate, and time.
// Version: 1.0

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var principal_text: UITextField!
    @IBOutlet weak var time_text: UITextField!
    @IBOutlet weak var roi_text: UITextField!
    @IBOutlet weak var total_label: UILabel!
    @IBOutlet weak var interest_label: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func Calculate(_ sender: Any) {
        if let principal = Double(principal_text.text ?? "0"),
                   let rate = Double(roi_text.text ?? "0"),
                   let time = Double(time_text.text ?? "0") {
                    
                    let interest = (principal * rate * time) / 100
                    let totalAmount = principal + interest
                    
                    total_label.text = String(format: "Total Amount: %.2f\nTotal Amount: %.2f", totalAmount)
            interest_label.text = String(format: "Interest: %.2f", interest)
                } else {
                    total_label.text = "Invalid input. Please enter valid numbers."
                }
    }
    
    @IBAction func clear_button(_ sender: Any) {
        principal_text.text = ""
        time_text.text = ""
        roi_text.text = ""
        total_label.text = ""
        interest_label.text = ""
    }
}

