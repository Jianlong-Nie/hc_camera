import React, {Component, createRef} from 'react';
import {
  UIManager,
  requireNativeComponent,
  findNodeHandle,
  NativeModules
} from 'react-native';

const NativePreviewComp = requireNativeComponent('PreviewViewManager', {
  name: 'PreviewViewManager',
  propTypes: {

  },
});
const { RNCamera } = NativeModules;

export class Preview extends Component {
    constructor(props) {
      super(props);
      this.nativeRef = createRef();
    }
    componentDidMount() {
      UIManager.dispatchViewManagerCommand(
        findNodeHandle(this.nativeRef.current),
        UIManager.PreviewViewManager.Commands.setupPreview,
        []
      )
    }
    render() {
      return <NativePreviewComp ref={this.nativeRef} {...this.props} />
    }
  }
export default RNCamera;
