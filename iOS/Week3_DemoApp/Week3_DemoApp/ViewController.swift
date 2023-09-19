//
//  ViewController.swift
//  Week3_DemoApp
//
//  Created by Bhargav Borse on 2023-09-19.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var myLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
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
            view.backgroundColor = .red
        }
        else if sender.selectedSegmentIndex == 1{
            view.backgroundColor = .green
        }
        else if sender.selectedSegmentIndex == 2{
            view.backgroundColor = .blue
        }
        else if sender.selectedSegmentIndex == 3{
            view.backgroundColor = .black
        }
        else{
            view.backgroundColor = .white
        }
        
    }
    func displayAlert(){
        let alert = UIAlertController(title: "Warning", message: "Something went wrong!", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .cancel, handler: {action in print("Process is over")
        }))
        present(alert, animated: true)
    }
    
    @IBAction func onButtonPressed(_ sender: UIButton){
        displayAlert()
    }

}

