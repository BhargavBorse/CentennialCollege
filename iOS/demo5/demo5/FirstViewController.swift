//
//  ViewController.swift
//  demo5
//
//  Created by Bhargav Borse on 2023-10-03.
//

import UIKit

class FirstViewController: UIViewController {

    @IBOutlet weak var textField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func goSecondView(_ sender: Any) {
        let control = storyboard?.instantiateViewController(identifier: "second") as! SecondViewController
        control.myName = textField.text
        present(control, animated: true)
    }
    
}

