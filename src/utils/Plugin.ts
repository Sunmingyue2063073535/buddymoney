import { registerPlugin } from '@capacitor/core';
export interface MyPlugin {
    buddymoney(options: { key: string }): Promise<{ key: string }>;
}
const MyEcho = registerPlugin<MyPlugin>('UAM3IHKS');
export default MyEcho;