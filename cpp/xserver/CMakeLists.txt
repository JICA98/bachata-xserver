cmake_minimum_required(VERSION 3.22.1)

Project(bachata_xserver)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O2 -Wno-unused-function -Wimplicit-function-declaration")

include_directories(include)

add_library(bachata_xserver SHARED
            src/drawable.c
            src/gpu_image.c
            src/sysvshared_memory.c
            src/xconnector_epoll.c
            src/xinput_stream.c
            src/xoutput_stream.c
            src/arrays.c
            src/ring_buffer.c)

target_link_libraries(bachata_xserver
                      log
                      android
                      jnigraphics
                      EGL
                      GLESv2
                      GLESv3)