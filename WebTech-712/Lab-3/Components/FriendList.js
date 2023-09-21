import React from "react";
import { View, FlatList, Text ,StyleSheet} from "react-native";
import ImageComponent from "./ImageComponent";

export default function FriendList() {
    const friendList = [
        { name: 'John',age: 20 , key: 123},
        { name: 'Mary' , age: 34, key: 111},
        { name: 'Lee' ,age : 22 , key: 922 },
        { name: 'Frad' ,age: 21, key: 134},
        { name: 'Sam' , age : 40, key: 888},
        {name: 'Smith', age: 33, key: 1244},
    ];
    return (
        <FlatList
            keyExtractor={friend => friend.key}
            data={friendList}
            renderItem={({ item }) => {
                return (
                    <Text style={styles.textView}>Name: {item.name} -Age: { item.age}</Text>
                );
        }}
        />
        
        // data
        // how each row/element looks like
        // index

        
    );
}

const styles = StyleSheet.create({

    textView:{
    marginTop:10,
    marginBottom:10,
    color: "gray",
    fontSize:20
  }
});
