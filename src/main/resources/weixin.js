{
	root: {
		type: "com.bj58.hrg.investment.wx.listener.impl.process.SyncThreadMode",
		singleton: true,
		init: "init",
	}
	lock: {
		type: "com.bj58.hrg.investment.wx.clust.JVMSynchronizer",
		singleton: true
	}
}