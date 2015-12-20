// gulp
var gulp = require('gulp');

// plugins
var jshint = require('gulp-jshint');
var uglify = require('gulp-uglify');
var minifyCSS = require('gulp-minify-css');
var rimraf = require('rimraf');
var runSequence = require('run-sequence');

// variables
var dest = './dist/';

// tasks
// code checking
gulp.task('lint', function(){

    gulp.src(['./app/**/*.js', '!./app/lib/**'])
        .pipe(jshint())
        .pipe(jshint.reporter('default'))
        .pipe(jshint.reporter('fail'));
});
// distributive directory cleaning
gulp.task('clean', function(cb) {
    rimraf(dest, cb);
});
// css minify
gulp.task('minify-css', function(){

    var opts = {comments: true, spare: true};
    gulp.src(['./app/**/*.css', '!./app/lib/**'])
        .pipe(minifyCSS(opts))
        .pipe(gulp.dest(dest));
});
// js minify
gulp.task('minify-js', function(){

    gulp.src(['./app/**/*.js', '!./app/lib/**'])
        .pipe(uglify())
        .pipe(gulp.dest(dest));
});
// copy bower libs
gulp.task('copy-bower-libs', function(){

    gulp.src('./app/lib/**')
        .pipe(gulp.dest('dist/lib'));
});
// copy html
gulp.task('copy-html-files', function(){

    gulp.src('./app/**/*.html')
       .pipe(gulp.dest('dist/'));
});


// default task (build)
gulp.task('default', function(){

    runSequence(
        ['clean'],
        ['lint', 'minify-css', 'minify-js', 'copy-html-files', 'copy-bower-libs']
    );
});