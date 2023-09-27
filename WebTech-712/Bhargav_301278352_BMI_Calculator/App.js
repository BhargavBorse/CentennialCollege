import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, Picker } from 'react-native';

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
      <Picker
        selectedValue={measurementSystem}
        style={styles.picker}
        onValueChange={(itemValue) => setMeasurementSystem(itemValue)}
      >
        <Picker.Item label="Metric (kg, cm)" value="metric" />
        <Picker.Item label="Imperial (lb, in)" value="imperial" />
      </Picker>
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
  picker: {
    width: '80%',
    marginTop: 10,
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

export default App;
