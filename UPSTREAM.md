# Bachata X Server

In-process X11 R11 server, ALSA audio server and SysV-SHM-over-ashmem support used by
Bachata S4 on Android.

## Provenance

- Seeded from: https://github.com/JICA98/winlator-app.git (runtime fork of brunodev85/winlator-app)
- Seed revision: 72ec347c9ced676e206fbc3762b9d567852cb3e3
- Seeded paths: `app/src/main/java/com/winlator/{xserver,alsaserver,sysvshm,xconnector,core,math,renderer}`
  and `app/src/main/cpp/winlator`
- Includes the Bachata S4 fork modifications recorded in Bachata S4's
  `runtime/vendor-overrides/**` at seed time (abstract X11 sockets, keymap query,
  GPU image unlock, SYNC_FD wait handling); per-file upstream/local SHA-256 provenance is in
  Bachata S4 `runtime/locks/winlator-vendor.sha256` at commit 47c13b89deba389cb9988f58622f76cd40e6b57a.
- Layout note: the historical `com.winlator.*` Java package and `winlator` CMake target names are
  retained intentionally in the seed so that Bachata can switch build inputs byte-identically;
  renaming to a Bachata namespace happens as a separate reviewed change.

## License

LGPL-2.1 (see LICENSE).
