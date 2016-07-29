/**
 * Created by admin on 2016/6/27.
 */

var picBanner = {
    properties: null
    ,saveComponent: function () {
        picBanner.properties.linkUrl = $(".picBUrl").val();
        if(picBanner.properties.pcImg=="" && picBanner.properties.mobileImg==""){
            layer.msg("组件数据缺少,未能保存,请完善。");
            return null;
        }
        return picBanner.properties;
    },
    uploadImage: function () {
        uploadForm( {
            ui: '#picBannerMaxImg',
            inputName: 'file',
            maxWidth: 1920,
            maxHeight: 200,
            isCongruent: false,
            maxFileCount: 1,
            successCallback: function(files, data, xhr, pd) {
                picBanner.properties.pcImg= data.fileUri;
            },
            deleteCallback: function (resp, data, jqXHR) {
               picBanner.properties.pcImg = "";
            }
        });
        uploadForm( {
            ui: '#picBannerMinImg',
            inputName: 'file',
            maxWidth: 1200,
            maxHeight: 200,
            isCongruent: false,
            maxFileCount: 1,
            successCallback: function(files, data, xhr, pd) {
                picBanner.properties.mobileImg = data.fileUri;
            },
            deleteCallback: function (resp, data, jqXHR) {
                picBanner.properties.mobileImg = "";
            }
        });
    },
    initProperties: function () {
        this.properties.pcImg = "";
        this.properties.mobileImg = "";
        this.properties.linkUrl = "";
    },
    init: function (globalId) {
        this.properties = widgetProperties(globalId);
        this.initProperties();
        this.uploadImage();
    }
};
