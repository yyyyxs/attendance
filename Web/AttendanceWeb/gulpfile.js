//connect������һ��web���Է��񣬷���http://localhost:8080 
var gulp = require('gulp'),
  connect = require('gulp-connect');
gulp.task('connect', function () {
    connect.server({
        port:8080,
		 root: './src/main/webapp/',
        livereload: true
    });
});
