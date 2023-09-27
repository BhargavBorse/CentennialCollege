import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet } from 'react-native';
import RNPickerSelect from 'react-native-picker-select';

const App = () => {
  const [weight, setWeight] = useState('');
  const [height, setHeight] = useState('');
  const [measurementSystem, setMeasurementSystem] = useState('metric');
  const [bmi, setBMI] = useState(null);

  const calculateBMI = () => {
    const weightInKg = measurementSystem === 'metric' ? parseFloat(weight) : parseFloat(weight) * 0.45;
    const heightInMeters = measurementSystem === 'metric' ? parseFloat(height) / 100 : parseFloat(height) * 0.0254;
    const bmiValue = (weightInKg / (heightInMeters * heightInMeters)).toFixed(2);
    setBMI(bmiValue);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>BMI Calculator</Text>
      <RNPickerSelect
        placeholder={{
          label: 'Select Measurement System',
          value: null,
        }}
        items={[
          { label: 'Metric (kg, cm)', value: 'metric' },
          { label: 'Imperial (lb, in)', value: 'imperial' },
        ]}
        onValueChange={(value) => setMeasurementSystem(value)}
        style={pickerSelectStyles}
        value={measurementSystem}
      />
      <TextInput
        placeholder="Weight"
        style={styles.input}
        onChangeText={(text) => setWeight(text)}
        value={weight}
        keyboardType="numeric"
      />
      <TextInput
        placeholder="Height"
        style={styles.input}
        onChangeText={(text) => setHeight(text)}
        value={height}
        keyboardType="numeric"
      />
      <Button title="Calculate BMI" onPress={calculateBMI} />
      {bmi && (
        <Text style={styles.result}>
          Your BMI: {bmi} -{' '}
          {bmi < 18.5
            ? 'Underweight'
            : bmi < 24.9
            ? 'Normal Weight'
            : bmi < 29.9
            ? 'Overweight'
            : 'Obese'}
        </Text>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
  },
  input: {
    width: '80%',
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginTop: 10,
    paddingLeft: 10,
  },
  result: {
    marginTop: 20,
    fontSize: 18,
    fontWeight: 'bold',
  },
});

const pickerSelectStyles = StyleSheet.create({
  inputIOS: {
    fontSize: 16,
    paddingVertical: 12,
    paddingHorizontal: 10,
    borderWidth: 1,
    borderColor: 'gray',
    borderRadius: 4,
    color: 'black',
    paddingRight: 30,
    marginTop: 10,
    width: '80%',
  },
  inputAndroid: {
    fontSize: 16,
    paddingHorizontal: 10,
    paddingVertical: 8,
    borderWidth: 0.5,
    borderColor: 'purple',
    borderRadius: 8,
    color: 'black',
    paddingRight: 30,
    marginTop: 10,
    width: '80%',
  },
});

export default App;
