import { StyleSheet, Text, View, TextInput, Button, Switch  } from 'react-native';
import React, { useState } from "react";
import ImageComponent from './Components/ImageComponent';
import FriendList from './Components/FriendList';
import ButtonsComponent from './Components/ButtonsComponent';

export default function App() {
  return (
    <View style={styles.container}>
      <ButtonsComponent></ButtonsComponent>
      {/* <ImageComponent
        imageSource={require('./assets/beach.jpg')}
        title="Beach Image" />
      <ImageComponent
        imageSource={require('./assets/forest.jpg')}
        title="Forest Image" />
      <ImageComponent
        imageSource={require('./assets/mountain.jpg')}
        title="Montain Image" />
      <FriendList/> */}
    </View>
  );
}

const styles = StyleSheet.create({
  darkBackground:{
    backgroundColor: '#000',
 },
 lightBackground:{
  backgroundColor: '#fff',
},
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  edit:{
    width:"90%",
    height:48,
    borderRadius:10,
    borderWidth:2,
    borderColor:"grey",
    fontSize:20,
    tintColor:"grey",
    color:"grey"
    
  },
  
  button:{
    width: 20,
    height: 20,
  
  },
  textView:{
    marginTop:10,
    marginBottom:10,
    color: "grey",
    fontSize:20
  }
});