import PhotosLayout from './components/layout/ImageLayout.vue'
import AlbumsLayout from './components/layout/AlbumsLayout.vue'
import UpPhoto from './components/UpPhoto.vue'
import LoginLayout from './components/layout/AdminLoginLayout.vue'

export default {
    routes: [
        {path: '/', component: PhotosLayout},
        {path: '/photos', component: PhotosLayout},
        {path: '/albums', component: AlbumsLayout},
        {path: '/upPhoto', component: UpPhoto},
        {path: '/searchPhotos', component: PhotosLayout},
        {path: '/searchAlbums', component: AlbumsLayout},
        {path: '/login', component: LoginLayout},

    ]
}