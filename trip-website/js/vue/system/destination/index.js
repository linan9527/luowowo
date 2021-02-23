var vue = new Vue({
    el:"#app",
    data:{
        regions:[],
        destListLeft:[],
        destListRight:[],
        regionId:''
    },
    methods:{
        hotChange:function (event) {
            var _this =$(event.currentTarget);
            var rid = _this.attr("rid");
            if(!rid){
                return;
            }
            $('.row-hot .r-navbar a').removeClass('on');
            _this.addClass('on');
            //国内数据列表
            ajaxGet("/destination/destsByRegionId",{regionId:rid}, function (data) {
                var map = data.data;
                var list = map.list;
                vue.regionId=map.regionId;
                vue.destListLeft = list.slice(0, list.length / 2 + 1);
                vue.destListRight = list.slice(list.length / 2 + 1, list.length);
            })
        }
    },
    mounted:function () {
        //热门数据
        ajaxGet("/destination/hotRegion",{}, function (data) {
            vue.$data.regions = data.data;
        })

        //国内数据列表
        ajaxGet("/destination/destsByRegionId",{regionId:'-1'}, function (data) {
            var map = data.data;
            var list = map.list;
            vue.regionId=map.regionId;
            vue.destListLeft = list.slice(0, list.length / 2 + 1);
            vue.destListRight = list.slice(list.length / 2 + 1, list.length);
        })
    }
});

