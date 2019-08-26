//如果wifi打开则关闭wifi，否则打开wifi
//isWifiOpen() ? closeWifi() : openWifi();

//音量小于50增加音量，否则降低音量
//curVol() < 50 ? volUp() : volDown();

//打开小爱捷径
//exec("am start -n com.miui.voiceassist/com.xiaomi.voiceassistant.AiSettings.AiShortcutActivity -a action.intent.action.VIEW");

//exec("am kill-all")

//拨打电话
//exec("am start -a android.intent.action.CALL -d tel:10010")

//打开网站
//exec("am start -a android.intent.action.VIEW -d  http://gityuan.com")

//打开支付宝扫一扫
//intent("alipayqr://platformapi/startapp?saId=10000007");

//打开微信扫一扫
//exec("am start -n com.tencent.mm/com.tencent.mm.plugin.scanner.ui.BaseScanUI");

//打开微信付款码
//exec("am start -n com.tencent.mm/com.tencent.mm.plugin.offline.ui.WalletOfflineCoinPurseUI");

//支付宝付款码
//intent("alipayqr://platformapi/startapp?saId=20000056")