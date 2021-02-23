var vue = new Vue({
    el:"#app",
    data:{
        abroadCds:{},
        chinaCds:{},
        hotCds:{},
        themeCds:{},
        commends:{},
        chinas:{},
        abroads:{},
        themes:{}
    },
    methods:{

    },
    mounted:function () {

        ajaxGet("/strategy/index",{}, function (data) {
            var map = data.data;

            vue.abroadCds=map.abroadCds;
            vue.chinaCds = map.chinaCds;
            vue.hotCds = map.hotCds;
            vue.themeCds = map.themeCds;
            vue.commends = map.commends;
            vue.chinas = map.chinas;
            vue.abroads = map.abroads;
            vue.themes = map.themes


            //分页
            buildPage(vue.page.number, vue.page.totalPages,vue.doPage);
        })
    }
});

