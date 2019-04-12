
# hc-camera

## Getting started

`$ npm install hc-camera --save`

### Mostly automatic installation

`$ react-native link hc-camera`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNCameraPackage;` to the imports at the top of the file
  - Add `new RNCameraPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':hc-camera'
  	project(':hc-camera').projectDir = new File(rootProject.projectDir, 	'../node_modules/hc-camera/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':hc-camera')
  	```


## Usage
```javascript
import RNCamera from 'hc-camera';

// TODO: What to do with the module?
RNCamera;
```
  