export default {
    name: 'CONST',
    data () {
        return {
            urlHead: "http://127.0.0.1:8080",
            setCommentsUrl: urlHead + "/photo/photoId?params=",
            setPopPhotoAlbumInfoUrl: urlHead + "/album/AlbumId?params=",
            getCommentsUrl: urlHead + "/photo/photoId?params=",
            getPopPhotoAlbumInfoUrl: urlHead + "/album/AlbumId?params=",
            getPagePhotosUrl: urlHead + "/photo/",
            addPagePhotosUrl: urlHead + "/photo/",
            addPageAlbumsUrl: urlHead + "/Album/",
            getSearchPhotosByPhotoNameUrl: urlHead + "/photo/photoName?params=",
            getSearchPhotosByAlbumNameUrl: urlHead + "/album/AlbumName?params=",
            getPageAlbumsUrl: urlHead + "/Album/",
            sendCommentUrl: urlHead + "/comment?person.personId=",
        }
    }
}