import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, TouchableOpacity } from 'react-native';

function LoginScreen({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);

  const handleLogin = () => {
    // Add your login logic here
    console.log('Logging in with username:', username, 'and password:', password);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.heading}>Login</Text>
      <TextInput
        style={styles.input}
        placeholder="Username"
        value={username}
        onChangeText={(text) => setUsername(text)}
      />
      <View style={styles.passwordContainer}>
        <TextInput
          style={styles.passwordInput}
          placeholder="Password"
          secureTextEntry={!showPassword}
          value={password}
          onChangeText={(text) => setPassword(text)}
        />
        <TouchableOpacity
          style={styles.showPasswordButton}
          onPress={() => setShowPassword(!showPassword)}
        >
          <Text>{showPassword ? 'Hide' : 'Show'}</Text>
        </TouchableOpacity>
      </View>
      <TouchableOpacity style={styles.loginButton} onPress={handleLogin}>
        <Text style={styles.loginButtonText}>Login</Text>
      </TouchableOpacity>
      <Text style={styles.signupText}>Not an existing user?</Text>
      <TouchableOpacity onPress={() => navigation.navigate('Register')}>
        <Text style={styles.signupLink}>Register here</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#EFE1E1',
  },
  heading: {
    fontSize: 32, // Increase the font size for the heading
    fontWeight: 'bold',
    marginBottom: 20,
    color: '#ED1703',
  },
  input: {
    width: 280, // Increase the width of the text inputs
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 10,
    paddingLeft: 10,
    borderRadius: 10, // Add border radius
  },
  passwordContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    position: 'relative',
  },
  passwordInput: {
    width: 280, // Increase the width of the password input
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 10,
    paddingLeft: 10,
    borderRadius: 10, // Add border radius
  },
  showPasswordButton: {
    position: 'absolute',
    right: 10,
    top: 10,
  },
  loginButton: {
    backgroundColor: '#ED1703',
    padding: 10,
    borderRadius: 10, // Add border radius
    width: 280, // Increase the width of the login button
  },
  loginButtonText: {
    color: 'white',
    textAlign: 'center',
  },
  signupText: {
    marginTop: 20,
    color: 'black',
  },
  signupLink: {
    color: 'blue',
    textDecorationLine: 'underline',
  },
});

export default LoginScreen;
