//
//  SecondViewController.swift
//  demo5
//
//  Created by Bhargav Borse on 2023-10-03.
//

import UIKit

class SecondViewController: UIViewController {

    @IBOutlet var lblName: UILabel!
    var myName: String?
    override func viewDidLoad() {
        super.viewDidLoad()
        lblName.text = myName
        // Do any additional setup after loading the view.
    }
    
    @IBAction func goRedButtonClicked(_ sender: Any) {
        view.backgroundColor = .red
    }
    
    @IBAction func goGreenButtonClicked(_ sender: Any) {
        view.backgroundColor = .green
    }
    @IBAction func goBlueButtonClicked(_ sender: Any) {
        view.backgroundColor = .blue
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
