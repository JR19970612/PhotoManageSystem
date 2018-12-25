/**
 * vuex配置
 */

import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);
let port = 8080;
let projectName = "/gdpi";
let head =
    "http://127.0.0.1:" + port + projectName;


//定义属性（数据）
var
    state = {
        // 首页图片和搜索图片信息
        photosPage: 1,
        AlbumsPage: 1,
        allPhotos: [],
        allAlbums: [],
        showAlbum: false,
        showPhoto: false,
        popPhotos: [{
            "photoId": 34,
            "photoName": "阿瑟东",
            "photoDesc": "s",
            "photoCreateTime": 1544711288000,
            "albumId": 1,
            "photoOriginalUrl": "",
            "photoThumUrl": ""
        }],
        popPhotoIndex: 0,
        popAlbumId: -1,
        comments: null,
        popPhotoAlbumInfo: [{
            "albumId": 1,
            "albumName": "相册1",
            "albumDesc": "相册一的描述",
            "albumCreatetime": 1544597136000,
            "photos": [{
                "photoId": 34,
                "photoName": "阿瑟东",
                "photoDesc": "s",
                "photoCreateTime": 1544711288000,
                "albumId": 1,
                "photoOriginalUrl": "",
                "photoThumUrl": ""
            }]
        }], URL: {
            setCommentsUrl: head + "/photo/photoId?params=",
            setPopPhotoAlbumInfoUrl: head + "/album/AlbumId?params=",
            getCommentsUrl: head + "/photo/photoId?params=",
            getPopPhotoAlbumInfoUrl: head + "/album/AlbumId?params=",
            getPagePhotosUrl: head + "/photo/",
            addPagePhotosUrl: head + "/photo/",
            addPageAlbumsUrl: head + "/Album/",
            getSearchPhotosByPhotoNameUrl: head + "/photo/photoName?params=",
            getSearchPhotosByAlbumNameUrl: head + "/album/AlbumName?params=",
            getPageAlbumsUrl: head + "/Album/",
            sendCommentUrl: head + "/comment?person.personId=",
            loginUrl: head + "/login",
        }
    };

//定义getters
var getters = {
    allPhotos(state) {
        return state.allPhotos;
    },
    showAlbum(state) {
        return state.showAlbum;
    },
    showPhoto(state) {
        return state.showPhoto;
    },
    popPhotos(state) {
        return state.popPhotos;
    },
    popPhotoIndex(state) {
        return state.popPhotoIndex;
    },
    comments(state) {
        return state.comments;
    },
    popPhotoAlbumInfo(state) {
        return state.popPhotoAlbumInfo;
    },
    popAlbumId(state) {
        return state.popAlbumId;
    },
    allAlbums(state) {
        return state.allAlbums;
    },
    albumsPage(state) {
        return state.albumsPage;
    },
    photosPage(state) {
        return state.photosPage;
    },
    URL(state) {
        return state.URL;
    }
};

//定义actions，要执行的操作，如流程判断、异步请求等
const actions = {
    setShowPhoto({commit, state}) {
        commit('setShowPhoto'); //提交一个名为increment的变化，名称可自定义，可以认为是类型名
    },
    setShowAlbum({commit, state}) {
        commit('setShowAlbum');
    },
    setPopPhotos({commit, state}, val) {
        console.log("setPopPhotos", val);
        commit('setPopPhotos', val);
    },
    incPopPhotoIndex({commit, state}) {
        commit('incPopPhotoIndex');

    },
    decPopPhotoIndex({commit, state}) {
        commit('decPopPhotoIndex');
    },
    setComments({commit, state}, photoId) {
        // 获取这个图片的评论
        var successCallback = response => {
            console.log("服务器请求成功了setComments");
            commit('setPopPhotoAlbumInfo',
                response.data.data[0].comments
            );
        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        Vue.http
            .get(state.URL.setCommentsUrl + photoId)
            .then(successCallback, errorCallback);
    },
    setPopPhotoAlbumInfo({commit, state}, albumId) {

        // 获取点击图片的相册信息
        var successCallback = response => {
            console.log("服务器请求成功了setPopPhotoAlbumInfo");
            commit('setPopPhotoAlbumInfo', response.data.data[0]);
        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        Vue.http
            .get(state.URL.setPopPhotoAlbumInfoUrl + albumId)
            .then(successCallback, errorCallback);
        // 获取这个图片的相册信息
    },
    setPopAlbumId({commit, state}, val) {
        commit('setPopAlbumId', val);
    },
    getComments({commit, state}, photoId) {
        // 获取点击图片的评论信息
        var successCallback = response => {
            console.log("服务器请求成功了setComments,photoId is " + photoId);
            commit(
                "setComments",
                response.data.data[0].comments
            );
        };
        var errorCallback = response => {
            console.log("get服务器请求出错了");
        };
        Vue.http
            .get(state.URL.getCommentsUrl + photoId)
            .then(successCallback, errorCallback);
    },
    getPopPhotoAlbumInfo({commit, state}, albumId) {
        // 获取点击图片的相册信息
        var successCallback = response => {
            console.log("服务器请求成功了 getPopPhotoAlbumInfo");
            commit(
                "setPopPhotoAlbumInfo",
                response.data.data[0]
            );
        };
        var errorCallback = response => {
            console.log("get服务器请求出错了");
        };
        Vue.http
            .get(state.URL.getPopPhotoAlbumInfoUrl + albumId)
            .then(successCallback, errorCallback);
    },
    getPagePhotos({commit}, {pageCount}) {
        var successCallback = response => {
            console.log("???123服务器请求成功了 getPagePhotos");
            console.log("设置分页为1");
            this.state.photosPage = 1;
            commit(
                "setAllPhotos",
                response.data.data
            );
        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        Vue.http
            .get(state.URL.getPagePhotosUrl + pageCount + "/" + 1)
            .then(successCallback, errorCallback);

    },
    addPagePhotos({commit}, {pageCount, page}) {
        var successCallback = response => {
            console.log("服务器请求成功了 addPagePhotos");
            if (response.data.data.length == 0) {
                Vue.prototype.$notify({
                    title: '警告',
                    message: '对不起，没有更多数据了.',
                    type: 'warning'
                });
            } else {
                commit(
                    "addPagePhotos",
                    response.data.data
                );
            }


        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        Vue.http
            .get(state.URL.addPagePhotos+ pageCount + "/" + (++this.state.photosPage))
            .then(successCallback, errorCallback);

    },
    addPageAlbums({commit}, {pageCount, page}) {
        var successCallback = response => {
            console.log("服务器请求成功了 addPageAlbums");
            if (response.data.data.length == 0) {
                Vue.prototype.$notify({
                    title: '警告',
                    message: '对不起，没有更多数据了.',
                    type: 'warning'
                });
            } else {
                commit(
                    "addPageAlbums",
                    response.data.data
                );

            }
        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        Vue.http
            .get(state.URL.addPageAlbumsUrl + pageCount + "/" + (++this.state.albumsPage))
            .then(successCallback, errorCallback);

    },
    getSearchPhotosByPhotoName({commit}, keyword) {
        var successCallback = response => {
            console.log("服务器请求成功了getPhotos");
            commit(
                "setAllPhotos",
                response.data.data
            );
        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        Vue.http
            .get(state.URL.getSearchPhotosByPhotoNameUrl + keyword)
            .then(successCallback, errorCallback);

    },
    getSearchPhotosByAlbumName({commit}, keyword) {
        var successCallback = response => {
            console.log("服务器请求成功了 getAllAlbums");
            console.log(
                response.data.data[0]
            );
            if (response.data.data[0] == null)
                commit("setAllAlbums", []);
            else {
                commit("setAllAlbums", response.data.data);
            }

        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        Vue.http
            .get(state.URL.getSearchPhotosByAlbumNameUrl + keyword)
            .then(successCallback, errorCallback);

    },
    getPageAlbums({commit}, {pageCount, page}) {
        var successCallback = response => {
            console.log("服务器请求成功了 getPageAlbums");
            // todo 获取最新的评论
            this.state.albumsPage = 1;
            commit(
                "setAllAlbums",
                response.data.data
            );
        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        Vue.http
            .get(state.URL.getPageAlbumsUrl+ pageCount + "/" + 1)
            .then(successCallback, errorCallback);

    },
    sendComment({commit}, obj) {
        var successCallback = response => {
            console.log("服务器请求成功了 sendComment");
            // 重新获取一次评论
            this.dispatch('getComments', obj.photoId);
        };
        var errorCallback = response => {
            console.log("服务器请求出错了");
        };
        var url = state.URL.sendCommentUrl
            + obj.personId + "&photoId=" + obj.photoId + "&commentContent=" + obj.commentContent;
        console.log(url);
        Vue.http
            .post(url)
            .then(successCallback, errorCallback);

    }
}

//定义mutations，处理状态（数据）的改变
const mutations = {
    setShowPhoto(state) {
        console.log("show popPhotoLayout now");
        state.showPhoto = !state.showPhoto;
    },
    setShowAlbum(state) {
        console.log("show popAlbum now");
        state.showAlbum = !state.showAlbum;
    },
    setPopAlbumId(state, val) {
        console.log("setPopAlbumId now");
        state.popAlbumId = val;
    },
    setPopPhotos(state, val) {
        console.log("设置图片索引对象", val);
        state.popPhotos = val.photos;
        state.popPhotoIndex = val.index;
    },
    addPagePhotos(state, val) {
        console.log("添加到 photos", val);
        state.allPhotos = state.allPhotos.concat(val);
    },
    addPageAlbums(state, val) {
        console.log("添加到 allAlbums", val);
        state.allAlbums = state.allAlbums.concat(val);
    },
    incPopPhotoIndex(state) {
        if (state.popPhotoIndex + 1 > state.popPhotos.length - 1) {
            state.popPhotoIndex = 0
        }
        else if (state.popPhotoIndex + 1 <= state.popPhotos.length - 1) {
            state.popPhotoIndex += 1;
        }
    },
    decPopPhotoIndex(state) {
        if (state.popPhotoIndex - 1 < 0) {
            state.popPhotoIndex = state.popPhotos.length - 1
        }
        else if (state.popPhotoIndex - 1 >= 0) {
            state.popPhotoIndex -= 1;
        }
    },
    setComments(state, obj) {
        console.log("获取到这个图片ID的评论内容:");
        console.log(obj);
        state.comments = obj;
    },
    setPopPhotoAlbumInfo(state, obj) {
        console.log("获取到这个图片ID的相册信息:");
        console.log(obj);
        state.popPhotoAlbumInfo = obj;
    },
    setAllPhotos(state, obj) {
        console.log("获取到这个所有图片信息:");
        console.log(obj);
        state.allPhotos = obj;
    },
    setAllAlbums(state, obj) {
        console.log("获取到这个所有相册信息:");
        console.log(obj);
        state.allAlbums = obj;
    }
}

//创建store对象
const store = new Vuex.Store({
    state,
    getters,
    actions,
    mutations
})

//导出store对象
export default store;
