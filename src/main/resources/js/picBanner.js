/**
 * Created by admin on 2016/6/27.
 */

CMSWidgets.initWidget({
// 编辑器相关
    editor: {
        properties: null,
        saveComponent: function (onSuccess, onFailed) {
            this.properties.linkUrl = $(".linkUrl").val();
            this.properties.bannerImgUri = $(".bannerImage").attr("src");
            this.properties.bannerImgPath = $(".bannerImage").attr("data-path");
            this.properties.bgColor = $(".bgColor").val();
            this.properties.marginTop = $(".marginTop").val();
            this.properties.marginBottom = $(".marginBottom").val();
            if (this.properties.bannerImgUri == "" && this.properties.bannerImgPath == "") {
                onFailed("组件数据缺少,未能保存,请完善。");
                return;
            }
            onSuccess(this.properties);
            return this.properties;
        },
        initProperties: function () {
            $('.js-addEditBtn').addEdit({
                amount: 6,
                title: 'banner',
                hasImage: true,
                imageClass: 'bannerImage',
                hasUrl: true,
                urlClass: 'linkUrl'
            });
        },
        open: function (globalId) {
            this.properties = widgetProperties(globalId);
            this.initProperties();
        },
        close: function (globalId) {

        }
    }
});
