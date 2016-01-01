var gulp = require('gulp');
var inject = require('gulp-inject');
var wiredep = require('wiredep').stream;r;

gulp.task('copy-fonts', function(){

    return gulp.src('./lib/bootstrap/fonts/**.*')
        .pipe(gulp.dest('./fonts'));
});

gulp.task('default', ['copy-fonts'], function () {

    var sources = gulp.src(['./js/**/*.js', './css/**/*.css'], {read: false});

    return gulp.src('./index.html')
        .pipe(wiredep({}))
        .pipe(inject(sources))
        .pipe(gulp.dest('./'));
});
