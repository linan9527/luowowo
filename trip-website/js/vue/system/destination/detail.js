var vue = new Vue({
    el:"#app",
    data:{
        toasts:[],
        dest:{},
        catalogs:[],
        strategies:[],
        page:{}
    },
    methods:{
        commPage:function (page) {
            var param = getParams();
            var p = $("#travelForm").serialize() + "&destId="+param.id + "&currentPage=" + page;
            //游记分页
            ajaxGet("/travel/query?"+p,{}, function (data) {
                vue.page = data.data;
                console.log(vue.page)
                buildPage(page, vue.page.totalPages, vue.doPage)
            })
        },
        doPage:function(page){
            this.commPage(page);
        },
        conditionChange:function(){
            this.commPage(1);
        }
    },
    mounted:function () {
        var param = getParams();

        ajaxGet("/destination/detail",{id:param.id}, function (data) {
            var map = data.data;

            vue.toasts = map.toasts;
            vue.dest = map.dest;
            vue.catalogs = map.catalogs;
            vue.strategies = map.strategies;
        })

        //游记分页
        ajaxGet("/travel/query",{destId:param.id}, function (data) {
            vue.page = data.data;
            buildPage(vue.page.number, vue.page.totalPages, vue.doPage)
        })
    }
});

