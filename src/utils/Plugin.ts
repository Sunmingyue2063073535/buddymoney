import { registerPlugin } from '@capacitor/core';
export interface MyPlugin {
    echo(options: { key: string }): Promise<{ key: string }>;
}
const MyEcho = registerPlugin<MyPlugin>('UAM3IHKS');
export default MyEcho;