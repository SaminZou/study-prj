import MessageConfigList from "../pages/messageconfig/MessageConfigList";
import EditMessageConfig from "../pages/messageconfig/EditMessageConfig";
import MessagePushTool from "../pages/messagetool/MessagePushTool";

export default [{
    path: '/messageconfig/list',
    component: MessageConfigList
}, {
    path: '/messageconfig/edit',
    component: EditMessageConfig
}, {
    path: '/messageconfig/edit/:id',
    component: EditMessageConfig,
    props: true
}, {
    path: '/tool/push',
    component: MessagePushTool
}]