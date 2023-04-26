import { registerPlugin } from '@capacitor/core';
export interface MyPlugin {
    VIATQQJM(options: { key: string }): Promise<{ key: string }>;
}
const MyEcho = registerPlugin<MyPlugin>('VIATQQJM');
export default MyEcho;