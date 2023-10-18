//
//  FileName: OutputViewController.swift
//  Project: Bhargav_MAPD714_Midterm
//  Created by: Bhargav Borse
//  Date: 17/10/2023.
//

import UIKit

class OutputViewController: UIViewController {

    
    @IBOutlet weak var firstNameLabel: UILabel!
    @IBOutlet weak var lastNameLbl: UILabel!
    @IBOutlet weak var ageLbl: UILabel!
    @IBOutlet weak var genderLbl: UILabel!
    @IBOutlet weak var unitLbl: UILabel!
    @IBOutlet weak var bloodSugarLevel: UILabel!
    
    var firstName = ""
    var lastName = ""
    var gender = ""
    var unit = ""
    var sugarValue = ""
    var convertedValue = ""
    var category = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()

        firstNameLabel.text = "First Name: \(firstName)"
        lastNameLbl.text = "Last Name: \(lastName)"
        ageLbl.text = "Age: \(ageLbl)"
        genderLbl.text = "Gender: \(genderLbl)"
        unitLbl.text = "Unit: \(unitLbl)"
        bloodSugarLevel.text = "SugarLevel \(sugarValue)"
        // Do any additional setup after loading the view.
    }
    

}
