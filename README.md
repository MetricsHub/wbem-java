# WBEM Java Client

![GitHub release (with filter)](https://img.shields.io/github/v/release/metricshub/wbem-java)
![Build](https://img.shields.io/github/actions/workflow/status/metricshub/wbem-java/deploy.yml)
![GitHub top language](https://img.shields.io/github/languages/top/metricshub/wbem-java)
![License](https://img.shields.io/github/license/metricshub/wbem-java)

This project is a fork of the excellent [Standards Based Linux Instrumentation](https://sourceforge.net/projects/sblim/) ([see also](https://sblim.sourceforge.net/wiki/index.php/Main_Page)).

See **[Project Documentation](https://metricshub.org/wbem-java)** and the [Javadoc](https://metricshub.org/wbem-java/apidocs) for more information on how to use this library in your code.

The Web-Based Enterprise Management (WBEM) Java Client is a library that enables to:
* Connect to a WBEM Server
* Execute WQL queries such as EnumerateInstances
It uses HTTP/HTTPS protocol for that purpose.

## Build instructions

This is a simple Maven project. Build with:

```bash
mvn verify
```

## Release instructions

The artifact is deployed to Sonatype's [Maven Central](https://central.sonatype.com/).

The actual repository URL is https://s01.oss.sonatype.org/, with server Id `ossrh` and requires credentials to deploy
artifacts manually.

But it is strongly recommended to only use [GitHub Actions "Release to Maven Central"](actions/workflows/release.yml) to perform a release:

* Manually trigger the "Release" workflow
* Specify the version being released and the next version number (SNAPSHOT)
* Release the corresponding staging repository on [Sonatype's Nexus server](https://s01.oss.sonatype.org/)
* Merge the PR that has been created to prepare the next version

## License

License is GNU General Lesser Public License (LGPL) version 3.0. Each source file includes the LGPL-3 header (build will fail otherwise).
To update source files with the proper header, simply execute the below command:

```bash
mvn license:update-file-header
```
