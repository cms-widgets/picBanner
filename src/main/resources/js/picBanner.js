/**
 * Created by admin on 2016/6/27.
 */

CMSWidgets.initWidget({
// 编辑器相关
    editor: {
        saveComponent: function (onFailed) {
            this.properties.bgColor = $(".bgColor").val();
            this.properties.marginTop = $(".marginTop").val();
            this.properties.marginBottom = $(".marginBottom").val();
            if (this.properties.serial == "" && this.properties.serial == "") {
                onFailed("组件数据缺少,未能保存,请完善。");
                return;
            }
        },
        initProperties: function () {
            $('.js-addEditBtn').addEdit({
                amount: 1,
                title: 'banner',
                hasUrl: true,
                urlClass: 'linkUrl'
            });
        },
        open: function (globalId) {
            this.initProperties();
        }
    }
});
