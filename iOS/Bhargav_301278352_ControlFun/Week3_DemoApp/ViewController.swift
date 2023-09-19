//
//  ViewController.swift
//  Week3_DemoApp
//
//  Created by Bhargav Borse on 2023-09-19.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var buttonDO: UIButton!
    @IBOutlet weak var switch2: UISwitch!
    @IBOutlet weak var switch1: UISwitch!
    @IBOutlet weak var numberField: UITextField!
    @IBOutlet weak var numberLabel: UILabel!
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet var myLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buttonDO.isHidden = true
        switch1.isHidden = true
        switch2.isHidden = true
        // Do any additional setup after loading the view.
    }
    @IBAction func onSlideChanged(_ sender: UISlider){
        myLabel.text = "\(lroundf(sender.value))"
    }
    @IBAction func onValueChanged(_ sender: UISwitch){
        if sender.isOn{
            view.backgroundColor = .red
        }
        else{
            view.backgroundColor = .green
        }
    }
    @IBAction func tabValueChanged(_ sender: UISegmentedControl){
        if sender.selectedSegmentIndex == 0{
            buttonDO.isHidden = true
            switch1.isHidden = false
            switch2.isHidden = false
        }
        else if sender.selectedSegmentIndex == 1{
            buttonDO.isHidden = false
            switch1.isHidden = true
            switch2.isHidden = true
        }
        
    }
    func displayAlert(){
        let alert = UIAlertController(title: "Something happened!", message: "Everything went well", preferredStyle: .actionSheet)
        alert.addAction(UIAlertAction(title: "Ok", style: .cancel, handler: {action in print("Process is over")
        }))
        
        present(alert, animated: true)
    }
    
    @IBAction func onButtonPressed(_ sender: UIButton){
        displayAlert()
    }

}

