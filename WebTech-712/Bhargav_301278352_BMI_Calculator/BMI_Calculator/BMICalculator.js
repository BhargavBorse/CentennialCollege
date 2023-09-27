import React, { useState } from 'react';
import { View, Text, TextInput, Button } from 'react-native';

const BMICalculator = () => {
  const [weight, setWeight] = useState('');
  const [height, setHeight] = useState('');
  const [isImperial, setIsImperial] = useState(false); // Use a boolean to toggle measurement system
  const [bmi, setBMI] = useState(null);
  const [bmiCategory, setBMICategory] = useState('');

  const calculateBMI = () => {
    if (weight && height) {
      const weightValue = parseFloat(weight);
      const heightValue = parseFloat(height);

      if (isImperial) {
        // Convert height from inches to meters
        const heightInMeters = heightValue * 0.0254;
        // Calculate BMI
        const bmiValue = (weightValue / (heightInMeters * heightInMeters)).toFixed(2);
        setBMI(bmiValue);
        
        // Determine BMI category for Imperial measurement
        let bmiCategory = '';
        if (bmiValue < 18.5) {
          bmiCategory = 'Underweight';
        } else if (bmiValue < 24.9) {
          bmiCategory = 'Normal Weight';
        } else if (bmiValue < 29.9) {
          bmiCategory = 'Overweight';
        } else {
          bmiCategory = 'Obese';
        }
        setBMICategory(bmiCategory);
      } else {
        // Calculate BMI for SI measurement system
        const bmiValue = (weightValue / (heightValue * heightValue)).toFixed(2);
        setBMI(bmiValue);

        // Determine BMI category for SI measurement
        let bmiCategory = '';
        if (bmiValue < 16) {
          bmiCategory = 'Severely Underweight';
        } else if (bmiValue < 16.9) {
          bmiCategory = 'Underweight';
        } else if (bmiValue < 24.9) {
          bmiCategory = 'Normal Weight';
        } else if (bmiValue < 29.9) {
          bmiCategory = 'Overweight';
        } else if (bmiValue < 34.9) {
          bmiCategory = 'Obesity Class I';
        } else if (bmiValue < 39.9) {
          bmiCategory = 'Obesity Class II';
        } else {
          bmiCategory = 'Obesity Class III';
        }
        setBMICategory(bmiCategory);
      }
    }
  };

  return (
    <View>
      <Text>Weight ({isImperial ? 'lb' : 'kg'}):</Text>
      <TextInput
        onChangeText={(text) => setWeight(text)}
        keyboardType="numeric"
        placeholder="Enter weight"
      />
      <Text>Height ({isImperial ? 'in' : 'cm'}):</Text>
      <TextInput
        onChangeText={(text) => setHeight(text)}
        keyboardType="numeric"
        placeholder="Enter height"
      />
      <View style={{ flexDirection: 'row', justifyContent: 'space-around' }}>
        <Button
          title="SI"
          onPress={() => setIsImperial(false)}
          color={!isImperial ? 'green' : undefined}
        />
        <Button
          title="Imperial"
          onPress={() => setIsImperial(true)}
          color={isImperial ? 'green' : undefined}
        />
      </View>
      <Button title="Calculate BMI" onPress={calculateBMI} />
      {bmi && (
        <View>
          <Text>Your BMI: {bmi}</Text>
          <Text>BMI Category: {bmiCategory}</Text>
        </View>
      )}
    </View>
  );
};

export default BMICalculator;
