cmake_minimum_required(VERSION 3.10.2)

set(BREAKPAD_ROOT ${CMAKE_ANDROID_JAR_DIRECTORIES})
include_directories(${BREAKPAD_ROOT} ${BREAKPAD_ROOT}/common/android/include)
file(GLOB BREAKPAD_SOURCES_COMMON
        break-pad.cpp
        ${BREAKPAD_ROOT}/client/linux/crash_generation/crash_generation_client.cc
        ${BREAKPAD_ROOT}/client/linux/dump_writer_common/thread_info.cc
        ${BREAKPAD_ROOT}/client/linux/dump_writer_common/ucontext_reader.cc
        ${BREAKPAD_ROOT}/client/linux/handler/exception_handler.cc
        ${BREAKPAD_ROOT}/client/linux/handler/minidump_descriptor.cc
        ${BREAKPAD_ROOT}/client/linux/log/log.cc
        ${BREAKPAD_ROOT}/client/linux/microdump_writer/microdump_writer.cc
        ${BREAKPAD_ROOT}/client/linux/minidump_writer/linux_dumper.cc
        ${BREAKPAD_ROOT}/client/linux/minidump_writer/linux_ptrace_dumper.cc
        ${BREAKPAD_ROOT}/client/linux/minidump_writer/minidump_writer.cc
        ${BREAKPAD_ROOT}/client/minidump_file_writer.cc
        ${BREAKPAD_ROOT}/common/android/breakpad_getcontext.S
        ${BREAKPAD_ROOT}/common/convert_UTF.cc
        ${BREAKPAD_ROOT}/common/md5.cc
        ${BREAKPAD_ROOT}/common/string_conversion.cc
        ${BREAKPAD_ROOT}/common/linux/elfutils.cc
        ${BREAKPAD_ROOT}/common/linux/file_id.cc
        ${BREAKPAD_ROOT}/common/linux/guid_creator.cc
        ${BREAKPAD_ROOT}/common/linux/linux_libc_support.cc
        ${BREAKPAD_ROOT}/common/linux/memory_mapped_file.cc
        ${BREAKPAD_ROOT}/common/linux/safe_readlink.cc)

file(GLOB BREAKPAD_ASM_SOURCE ${BREAKPAD_ROOT}/common/android/breakpad_getcontext.S)

set_source_files_properties(${BREAKPAD_ASM_SOURCE} PROPERTIES LANGUAGE C)

add_library( # Sets the name of the library.
             break-pad

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             ${BREAKPAD_SOURCES_COMMON} ${BREAKPAD_ASM_SOURCE})

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       break-pad

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )