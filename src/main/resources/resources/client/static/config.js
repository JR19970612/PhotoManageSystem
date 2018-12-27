let head =
    "http://127.0.0.1:8080/gdpi";
window.URLS = {
    setCommentsUrl: head + "/photo/photoId?params=",
    setPopPhotoAlbumInfoUrl: head + "/album/albumId?params=",
    getCommentsUrl: head + "/photo/photoId?params=",
    getPopPhotoAlbumInfoUrl: head + "/album/albumId?params=",
    getPagePhotosUrl: head + "/photo/",
    addPagePhotosUrl: head + "/photo/",
    addPageAlbumsUrl: head + "/album/",
    getSearchPhotosByPhotoNameUrl: head + "/photo/photoName?params=",
    getSearchPhotosByAlbumNameUrl: head + "/album/albumName?params=",
    getPageAlbumsUrl: head + "/album/",
    sendCommentUrl: head + "/comment?person.personId=",
    loginUrl: head + "/login",
};