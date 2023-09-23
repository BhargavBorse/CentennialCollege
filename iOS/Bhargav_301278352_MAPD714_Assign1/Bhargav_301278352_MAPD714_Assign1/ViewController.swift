//
//  ViewController.swift
//  File Name: Bhargav_301278352_MAPD714_Assign1
//  Author Name: Bhargav Borse
//  ID: 301278352
//  Date: 21/09/2023
//  App Desciption: Designed the User Interface (UI) for a Simple Interest calculator application while incorporating functional logic and an intuitive user experience.
//  Version: 1.0
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var total_label: UILabel!
    @IBOutlet weak var interest_label: UILabel!
    @IBOutlet weak var roi_text: UITextField!
    @IBOutlet weak var time_text: UITextField!
    @IBOutlet weak var principal_text: UITextField!
    
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
                    
                    total_label.text = String(totalAmount)
            interest_label.text = String(interest)
                } else {
                    total_label.text = "Invalid input. Please enter valid numbers."
                }
    }
    
    @IBAction func Clear(_ sender: Any) {
        principal_text.text = ""
        time_text.text = ""
        roi_text.text = ""
        total_label.text = "0"
        interest_label.text = "0"
    }
}

