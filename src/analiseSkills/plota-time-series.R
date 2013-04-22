time.series.plot = function(frame) {
  plot.ts(frame)
}

skills.iterate = function(files) {
  frame.files = c()
  for(f in files) {
    data = read.table(f, sep=";", header=F)
    frame.files = cbind(frame.files, data[, 2])
  }
  time.series.plot(frame.files)
}

skills.iterate(list.files("skillsInTime/"))

timestamp("1351114848")