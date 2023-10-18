//
//  FileName: ViewController.swift
//  Project: Bhargav_MAPD714_Midterm
//  Created by: Bhargav Borse
//  Date: 17/10/2023

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var firstNameText: UITextField!
    @IBOutlet weak var lastNameText: UITextField!
    @IBOutlet weak var genderSwitch: UISwitch!
    @IBOutlet weak var bloodSugarValue: UITextField!
    @IBOutlet weak var bloodUnit: UISegmentedControl!
    @IBOutlet weak var ageTextField: UITextField!
    
    //declaring variable
    var firstName: String!
    var lastName: String!
    var gender: String!
    var unit: String!
    var sugarValue: Double!
    var convertedValue: Double!
    var category: String!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
    }
    
    //calculate button function created
    @IBAction func calculateButtonPressed(_ sender: Any) {
        
        if let firstName = firstNameText.text,
           let lastName = lastNameText.text,
           let bloodSugarText = bloodSugarValue.text,
           let bloodSugarValue = Double(bloodSugarText) {
            
            let isMale = genderSwitch.isOn
            let selectedUnit = bloodUnit.selectedSegmentIndex == 0 ? "mmol/l" : "mg/dl"
            
            convertedValue = selectedUnit == "mmol/l" ? bloodSugarValue / 18.0 : bloodSugarValue * 18.0
            
            // Calculate the category
            
            let storyBoard = UIStoryboard(name: "Main", bundle: nil)
            let resultController = storyBoard.instantiateViewController(withIdentifier: "OutputViewController")
            
            resultController.loadViewIfNeeded()
            self.present(resultController, animated: true, completion: nil)
            
            let outpuVC = self.storyboard?.instantiateViewController(withIdentifier: "OutputViewController") as! OutputViewController
            self.navigationController?.pushViewController(outpuVC, animated: true)
            
            let outputVC = OutputViewController()
        outputVC.firstName = firstName
            outputVC.lastName = lastName
            outputVC.gender = isMale ? "Male" : "Female"
            outputVC.unit = selectedUnit
//            outputVC.sugarValue = bloodSugarValue
//            outputVC.convertedValue = convertedValue
            outputVC.category = category
            
            navigationController?.pushViewController(outputVC, animated: true)
            
        }
    }
    
    //clear button function created
    @IBAction func clearButtonPressed(_ sender: Any) {
        
    }
    
}

