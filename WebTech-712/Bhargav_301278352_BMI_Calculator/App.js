import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, ScrollView } from 'react-native';
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
      <View style={styles.header}>
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
      </View>
      <View style={styles.content}>
        <View style={styles.inputGroup}>
          <Text style={styles.inputLabel}>Weight</Text>
          <TextInput
            placeholder="e.g., 70"
            style={styles.input}
            onChangeText={(text) => setWeight(text)}
            value={weight}
            keyboardType="numeric"
          />
        </View>
        <View style={styles.inputGroup}>
          <Text style={styles.inputLabel}>Height</Text>
          <TextInput
            placeholder="e.g., 170"
            style={styles.input}
            onChangeText={(text) => setHeight(text)}
            value={height}
            keyboardType="numeric"
          />
        </View>
        <TouchableOpacity style={styles.calculateButton} onPress={calculateBMI}>
          <Text style={styles.buttonText}>Calculate BMI</Text>
        </TouchableOpacity>
      </View>
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
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f5f5f5', // Minimal background color
    padding: 20,
  },
  header: {
    alignItems: 'center',
    marginBottom: 20,
  },
  title: {
    fontSize: 32,
    fontWeight: 'bold',
    color: '#333',
  },
  content: {
    width: '100%',
  },
  inputGroup: {
    marginBottom: 15,
  },
  inputLabel: {
    fontSize: 18,
    color: '#333',
  },
  input: {
    height: 50,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 8,
    paddingLeft: 15,
    fontSize: 18,
    backgroundColor: 'white',
    color: '#333',
  },
  calculateButton: {
    backgroundColor: '#3498db',
    borderRadius: 8,
    paddingVertical: 15,
    paddingHorizontal: 30,
    alignItems: 'center',
    marginBottom: 20,
  },
  buttonText: {
    fontSize: 18,
    color: 'white',
    fontWeight: 'bold',
  },
  resultContainer: {
    alignItems: 'center',
  },
  resultLabel: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333',
    marginTop: 20,
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
    borderColor: '#ccc',
    borderRadius: 8,
    color: '#333',
    backgroundColor: 'white',
  },
  inputAndroid: {
    fontSize: 18,
    paddingHorizontal: 10,
    paddingVertical: 8,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 8,
    color: '#333',
    backgroundColor: 'white',
  },
});

export default App;
