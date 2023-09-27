import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, ScrollView } from 'react-native';
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
    <ScrollView contentContainerStyle={styles.container}>
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
        placeholder="Weight (kg or lb)"
        style={styles.input}
        onChangeText={(text) => setWeight(text)}
        value={weight}
        keyboardType="numeric"
      />
      <TextInput
        placeholder="Height (cm or in)"
        style={styles.input}
        onChangeText={(text) => setHeight(text)}
        value={height}
        keyboardType="numeric"
      />
      <Button title="Calculate BMI" onPress={calculateBMI} />
      {bmi && (
        <View style={styles.resultContainer}>
          <Text style={styles.resultLabel}>Your BMI:</Text>
          <Text style={styles.resultValue}>{bmi}</Text>
          <Text style={styles.resultCategory}>
            {bmi < 18.5
              ? 'Underweight'
              : bmi < 24.9
              ? 'Normal Weight'
              : bmi < 29.9
              ? 'Overweight'
              : 'Obese'}
          </Text>
        </View>
      )}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#f0f0f0',
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 20,
    color: '#333',
  },
  input: {
    width: '100%',
    height: 50,
    borderColor: '#ddd',
    borderWidth: 1,
    borderRadius: 8,
    paddingLeft: 15,
    marginBottom: 15,
    fontSize: 18,
    backgroundColor: 'white',
  },
  resultContainer: {
    marginTop: 30,
    alignItems: 'center',
  },
  resultLabel: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333',
  },
  resultValue: {
    fontSize: 48,
    fontWeight: 'bold',
    color: '#333',
  },
  resultCategory: {
    fontSize: 24,
    color: '#333',
  },
});

const pickerSelectStyles = StyleSheet.create({
  inputIOS: {
    fontSize: 18,
    paddingVertical: 12,
    paddingHorizontal: 10,
    borderWidth: 1,
    borderColor: '#ddd',
    borderRadius: 8,
    color: 'black',
    marginBottom: 15,
    backgroundColor: 'white',
  },
  inputAndroid: {
    fontSize: 18,
    paddingHorizontal: 10,
    paddingVertical: 8,
    borderWidth: 1,
    borderColor: '#ddd',
    borderRadius: 8,
    color: 'black',
    marginBottom: 15,
    backgroundColor: 'white',
  },
});

export default App;
