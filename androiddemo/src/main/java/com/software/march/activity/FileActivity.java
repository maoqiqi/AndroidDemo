package com.software.march.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.utils.SPUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private ImageView iv;
    private Button btnSave1;
    private Button btnRead1;
    private EditText etContent;
    private Button btnSave2;
    private Button btnRead2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_file);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv = (ImageView) findViewById(R.id.iv);
        btnSave1 = (Button) findViewById(R.id.btn_save_1);
        btnRead1 = (Button) findViewById(R.id.btn_read_1);
        etContent = (EditText) findViewById(R.id.et_content);
        btnSave2 = (Button) findViewById(R.id.btn_save_2);
        btnRead2 = (Button) findViewById(R.id.btn_read_2);

        btnSave1.setOnClickListener(this);
        btnRead1.setOnClickListener(this);
        btnSave2.setOnClickListener(this);
        btnRead2.setOnClickListener(this);

        // 内部存储
        Log.i(TAG, "getFilesDir()：" + getFilesDir().getAbsolutePath());
        Log.i(TAG, "getCacheDir()：" + getCacheDir().getAbsolutePath());

        // 使用外部存储--私有文件
        Log.i(TAG, "getExternalCacheDir()：" + getExternalCacheDir().getAbsolutePath());
        Log.i(TAG, "getExternalFilesDir()：" + getExternalFilesDir(null).getAbsolutePath());
        // 有时，已分配某个内部存储器分区用作外部存储的设备可能还提供了 SD 卡槽。
        // 在使用运行 Android 4.3 和更低版本的这类设备时，getExternalFilesDir() 方法将仅提供内部分区的访问权限，
        // 而您的应用无法读取或写入 SD 卡。不过，从 Android 4.4 开始，可通过调用 getExternalFilesDirs() 来同时访问两个位置，
        // 该方法将会返回包含各个位置条目的 File 数组。 数组中的第一个条目被视为外部主存储；除非该位置已满或不可用，否则应该使用该位置。
        // 如果您希望在支持 Android 4.3 和更低版本的同时访问两个可能的位置，
        // 请使用支持库中的静态方法 ContextCompat.getExternalFilesDirs()。
        // 在 Android 4.3 和更低版本中，此方法也会返回一个 File 数组，但其中始终仅包含一个条目。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (getExternalFilesDirs(null) != null) {
                Log.i(TAG, "getExternalFilesDirs()：");
                for (int i = 0; i < getExternalFilesDirs(null).length; i++) {
                    Log.i(TAG, "getExternalFilesDirs()：" + getExternalFilesDirs(null)[i].getAbsolutePath());
                }
            }
        }

        // 使用外部存储
        Log.i(TAG, "getRootDirectory()：" + Environment.getRootDirectory().getAbsolutePath());
        Log.i(TAG, "getExternalStorageDirectory()：" + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.i(TAG, "getExternalStoragePublicDirectory()：" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());

        // getFilesDir()：/data/user/0/com.software.march/files
        // getCacheDir()：/data/user/0/com.software.march/cache
        // getExternalCacheDir()：/storage/emulated/0/Android/data/com.software.march/cache
        // getExternalFilesDir()：/storage/emulated/0/Android/data/com.software.march/files
        // getExternalFilesDirs()：
        // getExternalFilesDirs()：/storage/emulated/0/Android/data/com.software.march/files
        // getRootDirectory()：/system
        // getExternalStorageDirectory()：/storage/emulated/0
        // getExternalStoragePublicDirectory()：/storage/emulated/0/Music
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_1:
                save1();
                break;
            case R.id.btn_read_1:
                read1();
                break;
            case R.id.btn_save_2:
                // save2();
                save3();
                break;
            case R.id.btn_read_2:
                // read2();
                read3();
                break;
        }
    }

    // 使用内部存储
    // 您可以直接在设备的内部存储中保存文件。
    // 默认情况下，保存到内部存储的文件是应用的私有文件，其他应用（和用户）不能访问这些文件。
    // 当用户卸载您的应用时，这些文件也会被移除。
    // 一、要创建私有文件并写入到内部存储：
    // 1.使用文件名称和操作模式调用 openFileOutput()。 这将返回一个 FileOutputStream。
    // 2.使用 write() 写入到文件。
    // 3.使用 close() 关闭流式传输。
    // MODE_PRIVATE 将会创建文件（或替换具有相同名称的文件），并将其设为应用的私有文件。
    // 其他可用模式包括：MODE_APPEND、MODE_WORLD_READABLE 和 MODE_WORLD_WRITEABLE。
    // 注：自 API 级别 17 以来，常量 MODE_WORLD_READABLE 和 MODE_WORLD_WRITEABLE 已被弃用。
    // 从 Android N 开始，使用这些常量将会导致引发 SecurityException。
    // 这意味着，面向 Android N 和更高版本的应用无法按名称共享私有文件，尝试共享“file://”URI 将会导致引发 FileUriExposedException。
    // 如果您的应用需要与其他应用共享私有文件，则可以将 FileProvider 与 FLAG_GRANT_READ_URI_PERMISSION 配合使用。
    // 二、要从内部存储读取文件：
    // 1.调用 openFileInput() 并向其传递要读取的文件名称。 这将返回一个 FileInputStream。
    // 2.使用 read() 读取文件字节。
    // 3.然后使用 close() 关闭流式传输。
    // 提示：如果在编译时想要保存应用中的静态文件，请在项目的 res/raw/ 目录中保存该文件。
    // 可以使用 openRawResource() 打开该资源并传递 R.raw.<filename> 资源 ID。
    // 此方法将返回一个 InputStream，您可以使用该流式传输读取文件（但不能写入到原始文件）。
    // 三、保存缓存文件
    // 如果您想要缓存一些数据，而不是永久存储这些数据，应该使用 getCacheDir() 来打开一个 File，
    // 它表示您的应用应该将临时缓存文件保存到的内部目录。
    // 当设备的内部存储空间不足时，Android 可能会删除这些缓存文件以回收空间。
    // 但您不应该依赖系统来为您清理这些文件， 而应该始终自行维护缓存文件，使其占用的空间保持在合理的限制范围内（例如 1 MB）。
    // 当用户卸载您的应用时，这些文件也会被移除。
    // 四、其他实用方法
    // getFilesDir():获取在其中存储内部文件的文件系统目录的绝对路径。
    // getDir():在您的内部存储空间内创建（或打开现有的）目录。
    // deleteFile():删除保存在内部存储的文件。
    // fileList():返回您的应用当前保存的一系列文件。

    private void save1() {
        // 读取assets下的logo.png
        try {
            // 1)得到AssetManager
            AssetManager manager = getAssets();
            // 2)得到InputStream
            InputStream is = manager.open("logo.png");
            // 3)得到OutputStream-->/data/data/packageName/files/logo.png
            FileOutputStream fos = openFileOutput("logo.png", Context.MODE_PRIVATE);
            // 4)边读边写
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            is.close();
            // 5)提示
            Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read1() {
        /*// 1)得到图片文件的路径-->/data/data/packageName/files
        String filesPath = getFilesDir().getAbsolutePath();
        String imagePath = filesPath + "/logo.png";
        // 2)读取加载图片文件得到bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        // 3)将其设置到imageView中显示
        iv.setImageBitmap(bitmap);*/

        try {
            InputStream is = openFileInput("logo.png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            iv.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 使用外部存储
    // 每个兼容 Android 的设备都支持可用于保存文件的共享“外部存储”。
    // 该存储可能是可移除的存储介质（例如 SD 卡）或内部（不可移除）存储。
    // 保存到外部存储的文件是全局可读取文件，而且，在计算机上启用 USB 大容量存储以传输文件后，可由用户修改这些文件。
    // 注意：如果用户在计算机上装载了外部存储或移除了介质，则外部存储可能变为不可用状态，
    // 并且在您保存到外部存储的文件上没有实施任何安全性。 所有应用都能读取和写入放置在外部存储上的文件，并且用户可以移除这些文件。
    // 一、使用作用域目录访问
    // 在 Android 7.0 或更高版本中，如果您需要访问外部存储上的特定目录，请使用作用域目录访问。
    // 作用域目录访问可简化您的应用访问标准外部存储目录（例如 Pictures 目录）的方式，并提供简单的权限 UI，
    // 清楚地详细介绍应用正在请求访问的目录。
    // 二、获取外部存储的访问权限
    // 要读取或写入外部存储上的文件，您的应用必须获取 READ_EXTERNAL_STORAGE 或 WRITE_EXTERNAL_STORAGE 系统权限。
    // 如果您同时需要读取和写入文件，则只需请求 WRITE_EXTERNAL_STORAGE 权限，因为此权限也隐含了读取权限要求。
    // 注：从 Android 4.4 开始，如果您仅仅读取或写入应用的私有文件，则不需要这些权限。
    // 三、检查介质可用性
    // 在使用外部存储执行任何工作之前，应始终调用 getExternalStorageState() 以检查介质是否可用。
    // 介质可能已装载到计算机，处于缺失、只读或其他某种状态。 例如，以下是可用于检查可用性的几种方法：
    /* Checks if external storage is available for read and write */
    /*public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }*/
    /* Checks if external storage is available to at least read */
    /*public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }*/
    // getExternalStorageState() 方法将返回您可能需要检查的其他状态（例如介质是否处于共享 [连接到计算]、完全缺失、错误移除等状态）。
    // 当您的应用需要访问介质时，您可以使用这些状态向用户通知更多信息。
    // 四、保存可与其他应用共享的文件
    // 一般而言，应该将用户可通过您的应用获取的新文件保存到设备上的“公共”位置，以便其他应用能够在其中访问这些文件，
    // 并且用户也能轻松地从该设备复制这些文件。 执行此操作时，应使用共享的公共目录之一，例如 Music/、Pictures/ 和 Ringtones/ 等。
    // 要获取表示相应的公共目录的 File，请调用 getExternalStoragePublicDirectory()，向其传递您需要的目录类型，
    // 例如 DIRECTORY_MUSIC、DIRECTORY_PICTURES、 DIRECTORY_RINGTONES 或其他类型。
    // 通过将您的文件保存到相应的媒体类型目录，系统的媒体扫描程序可以在系统中正确地归类您的文件（例如铃声在系统设置中显示为铃声而不是音乐）。
    // 例如，以下方法在公共图片目录中创建了一个用于新相册的目录：
    /*public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }*/
    // 五、保存应用私有文件
    // 如果您正在处理的文件不适合其他应用使用（例如仅供您的应用使用的图形纹理或音效），
    // 则应该通过调用 getExternalFilesDir() 来使用外部存储上的私有存储目录。
    // 此方法还会采用 type 参数指定子目录的类型（例如 DIRECTORY_MOVIES）。
    // 如果您不需要特定的媒体目录，请传递 null 以接收应用私有目录的根目录。
    // 从 Android 4.4 开始，读取或写入应用私有目录中的文件不再需要 READ_EXTERNAL_STORAGE 或 WRITE_EXTERNAL_STORAGE 权限。
    // 因此，您可以通过添加 maxSdkVersion 属性来声明，只能在较低版本的 Android 中请求该权限：
    // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="18" />
    // 注：当用户卸载您的应用时，此目录及其内容将被删除。
    // 此外，系统媒体扫描程序不会读取这些目录中的文件，因此不能从 MediaStore 内容提供程序访问这些文件。
    // 同样，不应将这些目录用于最终属于用户的媒体，例如使用您的应用拍摄或编辑的照片或用户使用您的应用购买的音乐等 — 这些文件应保存在公共目录中。
    // 有时，已分配某个内部存储器分区用作外部存储的设备可能还提供了 SD 卡槽。
    // 在使用运行 Android 4.3 和更低版本的这类设备时，getExternalFilesDir() 方法将仅提供内部分区的访问权限，而您的应用无法读取或写入 SD 卡。
    // 不过，从 Android 4.4 开始，可通过调用 getExternalFilesDirs() 来同时访问两个位置，该方法将会返回包含各个位置条目的 File 数组。
    // 数组中的第一个条目被视为外部主存储；除非该位置已满或不可用，否则应该使用该位置。
    // 如果您希望在支持 Android 4.3 和更低版本的同时访问两个可能的位置，请使用支持库中的静态方法 ContextCompat.getExternalFilesDirs()。
    // 在 Android 4.3 和更低版本中，此方法也会返回一个 File 数组，但其中始终仅包含一个条目。
    // 注意 尽管 MediaStore 内容提供程序不能访问 getExternalFilesDir() 和 getExternalFilesDirs() 所提供的目录，
    // 但其他具有 READ_EXTERNAL_STORAGE 权限的应用仍可访问外部存储上的所有文件，包括上述文件。
    // 如果您需要完全限制您的文件的访问权限，则应该转而将您的文件写入到内部存储。
    // 六、保存缓存文件
    // 要打开表示应该将缓存文件保存到的外部存储目录的 File，请调用 getExternalCacheDir()。
    // 如果用户卸载您的应用，这些文件也会被自动删除。
    // 与前述 ContextCompat.getExternalFilesDirs() 相似，您也可以通过调用 ContextCompat.getExternalCacheDirs()
    // 来访问辅助外部存储（如果可用）上的缓存目录。
    // 提示：为节省文件空间并保持应用性能，您应该在应用的整个生命周期内仔细管理您的缓存文件并移除其中不再需要的文件，这一点非常重要。


    private void save2() {
        // 1)判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // 2)读取输入的内容
                String content = etContent.getText().toString();
                // 3)得到指定文件的OutputStream
                // (1)得到sd卡下的files路径
                String filesPath = getExternalFilesDir(null).getAbsolutePath();
                String fileName = "test.txt";
                // (2)组成完整路径
                String filePath = filesPath + "/" + fileName;
                // (3)创建FileOutputStream
                FileOutputStream fos = new FileOutputStream(filePath);
                // 4)写数据
                fos.write(content.getBytes("utf-8"));
                fos.close();
                // 5)提示
                Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

    private void read2() {
        // 1)判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // 2)得到指定文件的InputStream
                // (1)得到sd卡下的files路径
                String filesPath = getExternalFilesDir(null).getAbsolutePath();
                String fileName = "test.txt";
                // (2)组成完整路径
                String filePath = filesPath + "/" + fileName;
                // (3)创建FileInputStream
                FileInputStream fis = new FileInputStream(filePath);
                // 3)读取数据,成String
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String content = baos.toString();
                // 4)显示
                etContent.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

    private void save3() {
        // 1)判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // 2)读取输入的内容
                String content = etContent.getText().toString();
                // 3)得到指定文件的OutputStream
                // (1)/storage/sdcard/
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                // (2)/storage/sdcard/march/(创建文件夹)
                File file = new File(sdPath + "/march");
                if (!file.exists()) {
                    file.mkdirs();// 创建文件夹
                }
                // (3)/storage/sdcard/march/test.txt
                String fileName = "test.txt";
                String filePath = sdPath + "/march/" + fileName;
                // (4)创建FileOutputStream
                FileOutputStream fos = new FileOutputStream(filePath);
                // 4)写数据
                fos.write(content.getBytes("utf-8"));
                fos.close();
                // 5)提示
                Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

    private void read3() {
        // 1)判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // 2)得到指定文件的InputStream
                // (1)/storage/sdcard/
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                String fileName = "test.txt";
                // (2)组成完整路径
                String filePath = sdPath + "/march/" + fileName;
                // (3)创建FileInputStream
                FileInputStream fis = new FileInputStream(filePath);
                // 3)读取数据,成String
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String content = baos.toString();
                // 4)显示
                etContent.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }
}