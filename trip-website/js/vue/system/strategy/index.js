var vue = new Vue({
    el:"#app",
    data:{
        destId:'',
        page:{},
        themes:{},
        toasts:[]
    },
    methods:{
        themeSelect:function(themeId, event){
            $("._j_tag").removeClass("on")
            $(event.currentTarget).addClass("on");
            vue.doPage(1);
        },
        doPage:function(page){

            var themeId = $("._j_tag.on").data("tid");
            ajaxGet("/strategy/query",{destId:vue.destId, themeId:themeId, currentPage:page}, function (data) {
                vue.page = data.data;
                vue.page.number = page;

                buildPage(vue.page.number, vue.page.totalPages,vue.doPage);
            })
        }
    },
    mounted:function () {
        //当前登录对象
        var param = getParams();
        this.destId = param.destId;
        //吐司
        ajaxGet("/strategy/list",{destId:param.destId}, function (data) {
            var map = data.data;
            vue.page = map.page;
            vue.themes = map.themes;
            vue.toasts = map.toasts;

            //分页
            buildPage(vue.page.number, vue.page.totalPages,vue.doPage);
        })
    }
});

