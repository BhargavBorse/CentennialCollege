import React from "react";
import { View, Text ,StyleSheet, Button, TouchableOpacity, TextInput} from "react-native";

export default function ButtonsComponent() {
    const [userName, setUserName] = React.useState("");
    const buttonClicked = () => {
        alert(`Thank ${userName} for entering your name`);
        setUserName("");
    }
    const textClicked = () => {
        alert('The Touchable Opacity Component Clicked');
    }
    return (
        <View>
            <TextInput style={styles.textinput}
                placeholder="Enter Your Name"
                onChangeText={value => setUserName(value) }
                value={userName}
            />

            <Button styles={styles.button}
                onPress={buttonClicked}
                title="Click Me Button">
                
            </Button>
            <TouchableOpacity
                styles={styles.button}
                onPress={textClicked}>
                {/* <ImageComponent
                    imageSource={require('../assets/beach.jpg')}
                     title="Beach Image" /> */}
                { <Text style={styles.textView}>Click Me Touchable Opacity</Text> }
            </TouchableOpacity>
        </View>

    );
}


const styles = StyleSheet.create({
  button: {
    alignSelf: "stretch",
    alignItems: "center",
    padding: 15,
    backgroundColor: "#59cbbd",
    marginTop: 10,
    },
     textinput: {
    alignSelf: "stretch",
    height: 40,
    marginBottom: 30,
    color: "#000",
    borderBottomColor: "#f8f8f8",
    borderBottomWidth: 1,
  },
      textView:{
    marginTop:10,
    marginBottom:10,
    color: "gray",
    fontSize:20
  }
    
});
