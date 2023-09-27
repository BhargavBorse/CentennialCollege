import { StatusBar } from 'expo-status-bar';
import { useEffect } from 'react';
import { FlatList, StyleSheet, Text, TouchableOpacity, View } from 'react-native';

export default function App() {

  const [data, setData] = useState([]); // 1
  useEffect(() => {
    console.log("Hello World");
  }
  , []);

  renderItem = ({data}) => {
    return (
      <TouchableOpacity>
        <Text></Text>
        <Text></Text>
        <Text></Text>
        <Text></Text>
      </TouchableOpacity>
    )
  }
  return (
    <View style={styles.container}>
      <FlatList data={bags} 
      renderItem={bag => renderItem(bag)}>
        <FlatList/>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
