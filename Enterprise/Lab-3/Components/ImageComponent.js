import React from "react";
import { View, Image, Text, StyleSheet } from "react-native";

export default function ImageComponent ({imageSource, title}) {
    
    return (
        <View style={styles.container}>
            <Image source={imageSource}></Image>
            <Text style={styles.textView}>{title}</Text>
    </View>);  
};

const styles = StyleSheet.create({
    container: {
        flexDirection: 'row',
        justifyContent:'space-between'
  },
 textView:{
    marginTop:10,
    marginBottom:10,
    color: "grey",
    fontSize:20
  }
});

